package com.example.myPlan.Tools;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TransfereDesk {

    public int startIdDesk;

    public int EndIdDesk;
    private Long id;

    public TransfereDesk(int startIdDesk, int endIdDesk) {
        this.startIdDesk = startIdDesk;
        EndIdDesk = endIdDesk;
    }

    public TransfereDesk() {

    }

    public int getStartIdDesk() {
        return startIdDesk;
    }

    public void setStartIdDesk(int startIdDesk) {
        this.startIdDesk = startIdDesk;
    }

    public int getEndIdDesk() {
        return EndIdDesk;
    }

    public void setEndIdDesk(int endIdDesk) {
        EndIdDesk = endIdDesk;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
