package com.hibernate.Entity;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Embeddable
public class Address {

        private String addressLine1;

        private String addressLine2;


        public Address() {

        }

        public Address(String addressLine1, String addressLine2) {
            this.addressLine1 = addressLine1;
            this.addressLine2 = addressLine2;

        }

        public String getAddressLine1() {
            return addressLine1;
        }

        public void setAddressLine1(String addressLine1) {
            this.addressLine1 = addressLine1;
        }

        public String getAddressLine2() {
            return addressLine2;
        }

        public void setAddressLine2(String addressLine2) {
            this.addressLine2 = addressLine2;
        }
}
