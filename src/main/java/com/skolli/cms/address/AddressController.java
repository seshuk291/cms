package com.skolli.cms.address;

import java.util.List;

import com.skolli.cms.users.dto.UsersDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.skolli.cms.address.dto.AddressDto;
import com.skolli.cms.utils.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/{userId}/{id}")
    public ResponseEntity<ApiResponse<AddressDto>> getAddressById(@PathVariable Long userId, @PathVariable Long id) {
        AddressDto addressDto = addressService.getAddressById(userId, id);
        return ResponseEntity.ok(ApiResponse.success("Success", addressDto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<AddressDto>>> getAddressesByUserId(@PathVariable Long userId) {
        List<AddressDto> addresses = addressService.getAddressByUserId(userId);
        return ResponseEntity.ok(ApiResponse.success("Success", addresses));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AddressDto>> createAddress(@RequestBody() AddressDto addressDto) {
        AddressDto createdAddress = this.addressService.createAddress(addressDto);
        return ResponseEntity.ok(ApiResponse.success("Address created successfully", createdAddress));
    }

    @PutMapping("/{userId}/{id}")
    public ResponseEntity<ApiResponse<AddressDto>> updateAddress(@PathVariable Long userId, @PathVariable Long id, Address address) {
        AddressDto updatedAddress = addressService.updateAddress(userId, id, address);
        return ResponseEntity.ok(ApiResponse.success("Address updated successfully", updatedAddress));
    }

    @DeleteMapping("/{userId}/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteAddress(@PathVariable Long userId, @PathVariable Long id) {
        Boolean deleted = addressService.deleteAddress(userId, id);
        return ResponseEntity.ok(ApiResponse.success("Address deleted successfully", deleted));
    }

}
