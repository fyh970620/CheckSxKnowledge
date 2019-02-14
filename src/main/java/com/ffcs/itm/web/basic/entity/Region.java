package com.ffcs.itm.web.basic.entity;

public class Region {
        
        private Long id;
        
        private Long parentId;
        
        private String name;
        
        private String sortId;

		private String regionLevel;
        
        private String regionDesc;
        
        private Long regionCode;

        private String state;
        
        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }
        
        public String getSortId() {
                return sortId;
        }

        public void setSortId(String sortId) {
                this.sortId = sortId;
        }

        public Long getParentId() {
            return parentId;
        }

        public void setParentId(Long parentId) {
            this.parentId = parentId;
        }
        

        public String getRegionLevel() {
			return regionLevel;
		}

		public void setRegionLevel(String regionLevel) {
			this.regionLevel = regionLevel;
		}

		public String getRegionDesc() {
			return regionDesc;
		}

		public void setRegionDesc(String regionDesc) {
			this.regionDesc = regionDesc;
		}

		public Long getRegionCode() {
			return regionCode;
		}

		public void setRegionCode(Long regionCode) {
			this.regionCode = regionCode;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}
        
}
