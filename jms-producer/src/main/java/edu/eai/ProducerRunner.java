package edu.eai;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProducerRunner implements CommandLineRunner {

    private final NumberSender sender;

    @Override
    public void run(String... args) {
        //sender.send("Hello World");
    }
}
