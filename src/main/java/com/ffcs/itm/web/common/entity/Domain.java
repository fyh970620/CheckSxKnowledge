package com.ffcs.itm.web.common.entity;

public class Domain {
        
        private String value;
        
        private String label;

        private String remark;

        private String domainCode;

        public String getValue() {
                return value;
        }

        public void setValue(String value) {
                this.value = value;
        }

        public String getLabel() {
                return label;
        }

        public void setLabel(String label) {
                this.label = label;
        }

        public String getRemark() {
                return remark;
        }

        public void setRemark(String remark) {
                this.remark = remark;
        }

        public String getDomainCode() {
                return domainCode;
        }

        public void setDomainCode(String domainCode) {
                this.domainCode = domainCode;
        }
}
