package ar.edu.itba.bd2.redmond.service;

import org.springframework.stereotype.Service;

@Service
public class SampleServiceImpl implements SampleService {

    @Override
    public void sampleServiceCall(int num) {
        System.out.println("The number is "+String.valueOf(num));
    }
}
