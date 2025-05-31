package br.com.cesarschool.presentation.dto.report;

import java.time.LocalDate;

public class ReportRequest {
        private Long studentId;
        private Long monitorId;
        private LocalDate attendanceDate;
        private String subject;
        private String description;

        public Long getStudentId() {
            return studentId;
        }

        public void setStudentId(Long studentId) {
            this.studentId = studentId;
        }

        public Long getMonitorId() {
            return monitorId;
        }

        public void setMonitorId(Long monitorId) {
            this.monitorId = monitorId;
        }

        public LocalDate getAttendanceDate() {
            return attendanceDate;
        }

        public void setAttendanceDate(LocalDate attendanceDate) {
            this.attendanceDate = attendanceDate;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }