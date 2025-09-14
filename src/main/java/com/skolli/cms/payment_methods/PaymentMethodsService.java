package com.skolli.cms.payment_methods;

import com.skolli.cms.common.custom_exceptions.*;
import com.skolli.cms.payment_methods.dto.CreatePaymentMethodDto;
import com.skolli.cms.payment_methods.dto.PaymentMethodItemDto;
import com.skolli.cms.payment_methods.dto.UpdatePaymentMethodDto;
import com.skolli.cms.users.UserRepository;
import com.skolli.cms.users.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentMethodsService {
    
    private final PaymentMethodsRepository paymentMethodsRepository;
    private final UserRepository userRepository;

    public List<PaymentMethodItemDto> findAllPaymentMethods() {
        return this.paymentMethodsRepository.findAll()
                .stream()
                .map(this::mapPaymentMethodToDto)
                .collect(Collectors.toList());
    }

    public PaymentMethodItemDto getPaymentMethodById(Long paymentMethodId) {
        PaymentMethods paymentMethod = this.paymentMethodsRepository.findById(paymentMethodId)
                .orElseThrow(() -> new PaymentMethodNotFoundException("Payment method not found with id: " + paymentMethodId));
        return mapPaymentMethodToDto(paymentMethod);
    }

    public List<PaymentMethodItemDto> getPaymentMethodsByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found with id: " + userId);
        }
        return this.paymentMethodsRepository.findByUserId(userId)
                .stream()
                .map(this::mapPaymentMethodToDto)
                .collect(Collectors.toList());
    }

    public PaymentMethodItemDto createPaymentMethod(CreatePaymentMethodDto paymentMethodDto) {
        Users user = this.userRepository.findById(paymentMethodDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + paymentMethodDto.getUserId()));
        
        try {
            PaymentMethods paymentMethod = PaymentMethods.builder()
                    .paymentType(paymentMethodDto.getPaymentType())
                    .paymentProcessor(paymentMethodDto.getPaymentProcessor())
                    .user(user)
                    .build();
            
            PaymentMethods savedPaymentMethod = this.paymentMethodsRepository.save(paymentMethod);
            return mapPaymentMethodToDto(savedPaymentMethod);
        } catch (Exception exception) {
            throw new PaymentMethodCreationException("Unable to create payment method: " + exception.getMessage());
        }
    }

    public Boolean updatePaymentMethod(Long paymentMethodId, UpdatePaymentMethodDto paymentMethodDto) {
        PaymentMethods paymentMethod = this.paymentMethodsRepository.findById(paymentMethodId)
                .orElseThrow(() -> new PaymentMethodNotFoundException("Payment method not found with id: " + paymentMethodId));
        
        try {
            paymentMethod.setPaymentType(paymentMethodDto.getPaymentType());
            paymentMethod.setPaymentProcessor(paymentMethodDto.getPaymentProcessor());
            this.paymentMethodsRepository.save(paymentMethod);
            return true;
        } catch (Exception exception) {
            throw new PaymentMethodUpdateException("Unable to update payment method: " + exception.getMessage());
        }
    }

    public Boolean deletePaymentMethod(Long paymentMethodId) {
        PaymentMethods paymentMethod = this.paymentMethodsRepository.findById(paymentMethodId)
                .orElseThrow(() -> new PaymentMethodNotFoundException("Payment method not found with id: " + paymentMethodId));
        
        // Check if payment method has associated transactions
        if (paymentMethod.getTransactions() != null && !paymentMethod.getTransactions().isEmpty()) {
            throw new PaymentMethodDeletionException("Cannot delete payment method with associated transactions");
        }
        
        try {
            this.paymentMethodsRepository.deleteById(paymentMethodId);
            return true;
        } catch (Exception exception) {
            throw new PaymentMethodDeletionException("Unable to delete payment method: " + exception.getMessage());
        }
    }

    private PaymentMethodItemDto mapPaymentMethodToDto(PaymentMethods paymentMethod) {
        return PaymentMethodItemDto.builder()
                .id(paymentMethod.getId())
                .paymentType(paymentMethod.getPaymentType())
                .paymentProcessor(paymentMethod.getPaymentProcessor())
                .userId(paymentMethod.getUser().getId())
                .userName(paymentMethod.getUser().getUserName())
                .build();
    }
}