package com.example.Theater.Booking.Service;

import com.example.Theater.Booking.DTO.PayamentDTO;
import com.example.Theater.Booking.DTO.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {


    public ResponseEntity<ResponseDTO> payment(PayamentDTO payamentDTO);
    }
