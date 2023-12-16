package com.stc.assessment.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "FILE")
public class Files {
    @Id
    @Column(name="ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    @Column(name="BINARY_CONTENT", columnDefinition = "bytea",nullable = false)
    private byte[] binaryContent;

    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;

    public Files() {
    }

    public Files(long id, byte[] binaryContent) {
        this.id = id;
        this.binaryContent = binaryContent;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getBinaryContent() {
        return binaryContent;
    }

    public void setBinaryContent(byte[] binaryContent) {
        this.binaryContent = binaryContent;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}

