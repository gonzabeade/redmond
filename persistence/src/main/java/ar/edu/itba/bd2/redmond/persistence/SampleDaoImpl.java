package ar.edu.itba.bd2.redmond.persistence;

import ar.edu.itba.bd2.redmond.model.SampleModel;
import org.springframework.stereotype.Repository;

@Repository
public class SampleDaoImpl implements SampleDao {

    @Override
    public SampleModel newSampleDao(String name) {
        return new SampleModel(name);

    }
}
