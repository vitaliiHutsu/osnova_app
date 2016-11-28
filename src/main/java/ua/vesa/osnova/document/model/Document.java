package ua.vesa.osnova.document.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import ua.vesa.osnova.document.valid.DocumentNumberValid;
import ua.vesa.osnova.document.valid.DocumentTitleValid;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
@Entity
@Table(name = "document")
public class Document implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(columnDefinition = "TEXT")
    @Size(max = 500, message = "длинное название")
    @DocumentTitleValid
    private String title;
    @Size(max = 255, message = "длинное название")
    private String number_doc;
    private String uuid;
    private Long date_add;
    private String date_document;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToOne
    @JoinColumn(name = "document_id")
    private Category category;

    public Document(int id) {
        this.id = id;
    }

    public Document() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNumber_doc() {
        return number_doc;
    }

    public void setNumber_doc(String number_doc) {
        this.number_doc = number_doc;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getDate_add() {
        return date_add;
    }

    public void setDate_add(Long date_add) {
        this.date_add = date_add;
    }

    public String getDate_document() {
        return date_document;
    }

    public void setDate_document(String date_document) {
        this.date_document = date_document;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
