package edu.eai;

import edu.eai.domain.NumberMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@RequiredArgsConstructor
@Component
public class ProducerRunner implements CommandLineRunner {

    private final NumberSender sender;

    @Override
    public void run(String... args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Insert number 1: ");
            int number1 = scanner.nextInt();
            System.out.print("Insert number 2: ");
            int number2 = scanner.nextInt();
            sender.send(new NumberMessage(number1, number2));
        }
    }
}
