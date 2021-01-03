package server.models.split;

import server.communication.ConnectionToClient;

import java.io.Serializable;
import java.util.Objects;

public class Participant implements Serializable {

    public Participant(ConnectionToClient clientConnection,int id,String nickname){
        this.clientConnection=clientConnection;
        this.amount=0;
        this.isReady=false;
        this.nickname=nickname;
        this.id=id;
    }

    @Override
    public String toString() {
        return "Participant{" +
                " id=" + id +
                ", amount=" + amount +
                ", isReady=" + isReady +
                ", nickname=" + nickname +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return id == that.id && Double.compare(that.amount, amount) == 0 && isReady == that.isReady && Objects.equals(nickname, that.nickname);
    }

    private transient ConnectionToClient clientConnection;
    private int id;
    private double amount;
    private boolean isReady;
    private String nickname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getNickname(){ return nickname; }

    public boolean isReady() {
        return isReady;
    }

    public ConnectionToClient getClientConnection() {
        return clientConnection;
    }

    /**
     * switch ready status
     */
    public void switchReadyStatus() {
        isReady = !isReady;
    }
}
