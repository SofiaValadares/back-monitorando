package br.com.cesarschool.domain.repository.user;

import br.com.cesarschool.domain.entity.AvailableTimeEntity;

import java.util.List;

public interface AvaliableTimeRepository {

    AvailableTimeEntity save(AvailableTimeEntity availableTime, Long monitorId);

    void deleteById(Long id);

    List<AvailableTimeEntity> findByMonitorId(Long monitorId);
}
