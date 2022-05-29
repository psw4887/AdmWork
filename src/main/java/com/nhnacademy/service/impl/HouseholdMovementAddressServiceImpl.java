package com.nhnacademy.service.impl;

import com.nhnacademy.domain.vo.MovementRequest;
import com.nhnacademy.entity.Household;
import com.nhnacademy.entity.HouseholdMovementAddress;
import com.nhnacademy.exception.HouseHoldNotFoundException;
import com.nhnacademy.exception.HouseholdMovementNotFoundException;
import com.nhnacademy.repository.HouseholdMovementAddressRepository;
import com.nhnacademy.repository.HouseholdRepository;
import com.nhnacademy.service.HouseholdMovementAddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;

@Service("householdMovementAddressService")
public class HouseholdMovementAddressServiceImpl implements HouseholdMovementAddressService {
    private final HouseholdMovementAddressRepository addressRepository;
    private final HouseholdRepository householdRepository;

    public HouseholdMovementAddressServiceImpl(HouseholdMovementAddressRepository addressRepository, HouseholdRepository householdRepository) {
        this.addressRepository = addressRepository;
        this.householdRepository = householdRepository;
    }

    @Override
    @Transactional
    public void movementRegister(int hNum, MovementRequest request) {
        HouseholdMovementAddress address = new HouseholdMovementAddress();
        HouseholdMovementAddress.HouseholdMovementAddressPK pk = new HouseholdMovementAddress.HouseholdMovementAddressPK();
        Household hold = householdRepository.findById(hNum).orElseThrow(HouseHoldNotFoundException::new);

        pk.setMovementReportDate(getTime());
        pk.setHouseholdSerialNumber(hNum);

        address.setHouseholdMovementAddressPK(pk);
        address.setHousehold(hold);
        address.setHouseMovementAddress(request.getAddress());
        address.setLastAddressYN(request.getIsFinal());

        addressRepository.save(address);
    }

    @Override
    @Transactional
    public void movementModify(int hNum, Date date, MovementRequest movementRequest) {
        HouseholdMovementAddress.HouseholdMovementAddressPK pk = new HouseholdMovementAddress.HouseholdMovementAddressPK();
        pk.setHouseholdSerialNumber(hNum);
        pk.setMovementReportDate(date);
        HouseholdMovementAddress address = addressRepository.findById(pk).orElseThrow(HouseholdMovementNotFoundException::new);

        address.setHouseMovementAddress(movementRequest.getAddress());
        address.setLastAddressYN(movementRequest.getIsFinal());
    }

    @Override
    @Transactional
    public void movementDelete(int hNum, Date date) {
        HouseholdMovementAddress.HouseholdMovementAddressPK pk = new HouseholdMovementAddress.HouseholdMovementAddressPK();
        pk.setHouseholdSerialNumber(hNum);
        pk.setMovementReportDate(date);

        addressRepository.deleteById(pk);
    }

    private Timestamp getTime() {
        return new Timestamp(new Date().getTime());
    }
}
