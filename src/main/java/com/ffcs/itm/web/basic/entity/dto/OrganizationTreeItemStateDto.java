package com.ffcs.itm.web.basic.entity.dto;

public class OrganizationTreeItemStateDto {
        
        private boolean opened;
        
        private boolean loaded;
        
        public boolean isOpened() {
                return opened;
        }

        public void setOpened(boolean opened) {
                this.opened = opened;
        }

        public boolean isLoaded() {
                return loaded;
        }

        public void setLoaded(boolean loaded) {
                this.loaded = loaded;
        }
        
}
