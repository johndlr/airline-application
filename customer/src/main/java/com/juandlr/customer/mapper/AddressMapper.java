package com.juandlr.customer.mapper;

import com.juandlr.customer.dto.AddressDto;
import com.juandlr.customer.entity.Address;

public class AddressMapper {

    public static Address mapToAddress(Address address, AddressDto addressDto){
        address.setStreet(addressDto.getStreet());
        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        address.setZipCode(addressDto.getZipCode());
        return address;
    }

    public static AddressDto mapToAddressDto(Address address, AddressDto addressDto){
        addressDto.setStreet(address.getStreet());
        addressDto.setCity(address.getCity());
        addressDto.setState(address.getState());
        addressDto.setZipCode(address.getZipCode());
        return addressDto;
    }
}
