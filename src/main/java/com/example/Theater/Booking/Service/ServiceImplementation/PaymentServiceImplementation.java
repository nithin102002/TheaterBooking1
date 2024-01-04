package com.example.Theater.Booking.Service.ServiceImplementation;

import com.example.Theater.Booking.DTO.PayamentDTO;
import com.example.Theater.Booking.DTO.ResponseDTO;
import com.example.Theater.Booking.Model.Booking;
import com.example.Theater.Booking.Model.Payment;
import com.example.Theater.Booking.Repository.BookingRepository;
import com.example.Theater.Booking.Repository.PaymentRepository;
import com.example.Theater.Booking.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentServiceImplementation implements PaymentService {
@Autowired
    BookingRepository bookingRepository;
@Autowired
    PaymentRepository paymentRepository;

    @Override
    public ResponseEntity<ResponseDTO> payment(PayamentDTO payamentDTO) {

        Payment payment= Payment.builder().booking(bookingRepository.findById(payamentDTO.getBookingId()).orElseThrow())
                .paymentType(payamentDTO.getPaymentType())
                .TotalAmount(payamentDTO.getTotalAmount())
                .status("True").build();

        Payment savedPaymentDetails= paymentRepository.save(payment);
        System.out.println(payment);
        long bookId= payamentDTO.getBookingId();
        if(payment.getStatus().equals("True")){
            Optional<Booking> booking=bookingRepository.findById(bookId);
            booking.get().setStatus("Booked");
            bookingRepository.save(booking.get());
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK,"Payment_Details",savedPaymentDetails));
    }
}
