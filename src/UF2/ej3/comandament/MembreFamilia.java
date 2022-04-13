package UF2.ej3.comandament;

public class MembreFamilia extends Thread{

    private Comandament com;

    public MembreFamilia(String name, Comandament com){
        super(name);
        this.com = com;
    }

    @Override
    public void run(){
        for (;;) {
            com.agafar();
            System.out.println(getName() + " mirando tele");
            try{
                Thread.sleep((long) Math.random()*50);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            com.deixar();

            try{
                Thread.sleep((long) Math.random()*500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
