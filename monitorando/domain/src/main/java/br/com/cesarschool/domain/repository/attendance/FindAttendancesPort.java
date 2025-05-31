package br.com.cesarschool.domain.repository.attendance;

import br.com.cesarschool.domain.entity.AttendanceEntity;

import java.util.List;

public interface FindAttendancesPort {
    List<AttendanceEntity> findByStudentIdAndMonitorId(Long studentId, Long monitorId);
}
