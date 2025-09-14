package com.skolli.cms.address;

import java.util.List;

import com.skolli.cms.common.custom_exceptions.UserNotFoundException;
import com.skolli.cms.users.UserRepository;
import com.skolli.cms.users.Users;
import org.apache.catalina.User;
import org.hibernate.boot.model.process.internal.UserTypeResolution;
import org.springframework.stereotype.Service;
import com.skolli.cms.address.dto.AddressDto;
import com.skolli.cms.common.custom_exceptions.AddressNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public AddressDto saveAddress(Address address) {
        Address savedAddress = addressRepository.save(address);
        return mapToDto(savedAddress);
    }

    public AddressDto getAddressById(Long userId, Long id) {
        Address address = addressRepository.findByIdAndUserId(id, userId)
        .orElseThrow(() -> new AddressNotFoundException("Address with id " + id + " not found for user id " + userId));
        return mapToDto(address);
    }

    public List<AddressDto> getAddressByUserId(Long userId) {
        List<Address> addresses = addressRepository.findAddressByUserId(userId)
            .orElseThrow(() -> new AddressNotFoundException("Address not found for user id " + userId));

        return addresses.stream().map(this::mapToDto).toList();
    }

    public AddressDto createAddress(AddressDto addressDto) {
        Users user = this.userRepository.findById(addressDto.userId())
                .orElseThrow(() -> new UserNotFoundException("User with id: " + addressDto.userId() + " Not Found"));

        Address address = Address.builder()
                .address1(addressDto.address1())
                .address2(addressDto.address2())
                .city(addressDto.city())
                .state(addressDto.state())
                .zipCode(addressDto.zipCode())
                .country(addressDto.country())
                .build();

        return this.mapToDto(address);
    }

    public Boolean deleteAddress(Long userId, Long id) {
        Address addressToDelete = addressRepository.findByIdAndUserId(id, userId)
            .orElseThrow(() -> new AddressNotFoundException("Address not found for user id " + userId));
        addressRepository.delete(addressToDelete);
        return !addressRepository.existsById(id);
    }

    public AddressDto updateAddress(Long userId, Long id, Address updatedAddress) {
        return addressRepository.findByIdAndUserId(id, userId).map(address -> {
            address.setAddress1(updatedAddress.getAddress1());
            address.setAddress2(updatedAddress.getAddress2());
            address.setCity(updatedAddress.getCity());
            address.setState(updatedAddress.getState());
            address.setZipCode(updatedAddress.getZipCode());
            Address savedAddress = addressRepository.save(address);
            return mapToDto(savedAddress);
        }).orElseThrow(() -> new AddressNotFoundException("Address with id " + id + " not found"));
    }

    private AddressDto mapToDto(Address address) {
        return new AddressDto(
            address.getId(),
            address.getAddress1(),
            address.getAddress2(),
            address.getCity(),
            address.getState(),
            address.getZipCode(),
            address.getCountry(),
            address.getUser().getId()
        );
    }
}
