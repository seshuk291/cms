package com.skolli.cms.address;

import java.util.List;

import org.springframework.stereotype.Service;
import com.skolli.cms.address.dto.AddressDto;
import com.skolli.cms.common.custom_exceptions.AddressNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AddressService {
    private final AddressRepository addressRepository;

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
            address.getZipCode()
        );
    }
}
