package com.csit.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SysTemplate_detail {
        private int template_id;
        private String control_name;
        private String location_x;
        private String location_y;
        private String control_type;
        private String control_shape;
        private String qrcode;
        private String device_name;
        private int idle;
        private int depart;

        public int getIdle() {
                return idle;
        }

        public void setIdle(int idle) {
                this.idle = idle;
        }

        public int getDepart() {
                return depart;
        }

        public void setDepart(int depart) {
                this.depart = depart;
        }

        public String getDevice_name() {
                return device_name;
        }

        public void setDevice_name(String device_name) {
                this.device_name = device_name;
        }

        public int getTemplate_id() {
                return template_id;
        }

        public void setTemplate_id(int template_id) {
                this.template_id = template_id;
        }

        public String getControl_name() {
                return control_name;
        }

        public void setControl_name(String control_name) {
                this.control_name = control_name;
        }

        public String getLocation_x() {
                return location_x;
        }

        public void setLocation_x(String location_x) {
                this.location_x = location_x;
        }

        public String getLocation_y() {
                return location_y;
        }

        public void setLocation_y(String location_y) {
                this.location_y = location_y;
        }

        public String getControl_type() {
                return control_type;
        }

        public void setControl_type(String control_type) {
                this.control_type = control_type;
        }

        public String getControl_shape() {
                return control_shape;
        }

        public void setControl_shape(String control_shape) {
                this.control_shape = control_shape;
        }

        public String getQrcode() {
                return qrcode;
        }

        public void setQrcode(String qrcode) {
                this.qrcode = qrcode;
        }

        @Override
        public String toString() {
                return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                        .append("template_id", getTemplate_id())
                        .append("control_name", getControl_name())
                        .append("location_x", getLocation_x())
                        .append("location_y", getLocation_y())
                        .append("control_type", getControl_type())
                        .append("control_shape", getControl_shape())
                        .append("qrcode", getQrcode())
                        .append("device_name", getDevice_name())
                        .append("idle", getIdle())
                        .append("depart", getDepart())
                        .toString();
        }


}
