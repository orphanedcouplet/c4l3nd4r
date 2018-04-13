package com.oliverandcode.c4l3nd4r.models.data;

import com.oliverandcode.c4l3nd4r.models.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface LocationDao extends CrudRepository<Location, Integer> {
}
