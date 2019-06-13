package com.etaTech.Hello;

/****************************************************
 *** Created by Fady Fouad on 6/13/2019 at 13:37.***
 ***************************************************/
public class Main {
    public static void main(String[] args) {
        PolitePerson person = new PolitePerson("Fady");
        PolitePerson person1 = new PolitePerson("Name");


        new Thread(() -> person1.sayHello(person)).start();
        new Thread(() -> person.sayHello(person1)).start();

    }
    static class PolitePerson{
        private final String name ;

        PolitePerson(String name) {
            this.name = name;
        }

        synchronized void sayHello(PolitePerson person){
            System.out.format("%s : %s"+" Said hello \n",this.name,person.getName());
            person.sayHelloBack(this);
        }
        synchronized void sayHelloBack(PolitePerson person){
            System.out.format("%s : %s"+" Said hello back \n",this.name,person.getName());
        }

         String getName() {
            return name;
        }
    }
}