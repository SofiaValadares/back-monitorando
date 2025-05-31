package br.com.cesarschool.infrastructure.adapter.attendance;

import br.com.cesarschool.domain.entity.AttendanceEntity;
import br.com.cesarschool.domain.repository.attendance.FindAttendancesPort;
import br.com.cesarschool.infrastructure.persistence.mapper.AttendanceMapper;
import br.com.cesarschool.infrastructure.repository.AttendanceJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FindAttendancesAdapter implements FindAttendancesPort {

    private final AttendanceJpaRepository attendanceRepository;

    public FindAttendancesAdapter(AttendanceJpaRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public List<AttendanceEntity> findByStudentIdAndMonitorId(Long studentId, Long monitorId) {
        return attendanceRepository.findByStudent_IdAndMonitor_Id(studentId, monitorId)
                .stream()
                .map(AttendanceMapper::toDomain)
                .collect(Collectors.toList());
    }
}
