package com.example.vehicle.soapconfig.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class VehicleConfiguration{

        @Bean
        public Jaxb2Marshaller marshaller() {
            Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
            marshaller.setPackagesToScan("com.example.vehicle.xmlmodel.vehicle");
            return marshaller;
        }

        @Bean
        public VehicleClient vehicleClient(Jaxb2Marshaller marshaller) {
            VehicleClient client = new VehicleClient();
            client.setDefaultUri("http://localhost:8080/ws");
            client.setMarshaller(marshaller);
            client.setUnmarshaller(marshaller);
            return client;
        }

}
