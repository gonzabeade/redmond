package ar.edu.itba.bd2.redmond.service;

import ar.edu.itba.bd2.redmond.model.MyEvent;
import ar.edu.itba.bd2.redmond.model.SampleModel;
import ar.edu.itba.bd2.redmond.persistence.SampleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SampleServiceImpl implements SampleService {

    final private SampleDao sampleDao;
    final private TransactionEventDao transactionEventDao;
    @Autowired
    public SampleServiceImpl(SampleDao sampleDao, TransactionEventDao transactionEventDao) {
        this.sampleDao = sampleDao;
        this.transactionEventDao = transactionEventDao;
    }

    @Override
    public SampleModel sampleServiceCall(int num) {
        transactionEventDao.publishEvent("Hola! Gracias por pushear!");
        return sampleDao.newSampleDao("Gonzalo"+num);
    }
}
