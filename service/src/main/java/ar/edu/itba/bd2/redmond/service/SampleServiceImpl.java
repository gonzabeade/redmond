package ar.edu.itba.bd2.redmond.service;

import ar.edu.itba.bd2.redmond.model.SampleModel;
import ar.edu.itba.bd2.redmond.persistence.SampleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SampleServiceImpl implements SampleService {

    final private SampleDao sampleDao;
    @Autowired
    public SampleServiceImpl(SampleDao sampleDao) {
        this.sampleDao = sampleDao;
    }

    @Override
    public SampleModel sampleServiceCall(int num) {
        return sampleDao.newSampleDao("Gonzalo"+num);
    }
}
