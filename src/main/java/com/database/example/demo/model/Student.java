/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.database.example.demo.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


@Entity
@Table(name = "student", catalog = "", schema = "", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id_"})})
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s")
        , @NamedQuery(name = "Student.findById", query = "SELECT s FROM Student s WHERE s.id = ?1")
        , @NamedQuery(name = "Student.findByName", query = "SELECT s FROM Student s WHERE s.name = ?1")})
public final class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name_", nullable = false, length = 50)
    private String name;
    @JoinColumn(name = "class_id", referencedColumnName = "id_", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Class_ classId;
    @JoinColumn(name = "teacher_id", referencedColumnName = "id_", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Teacher teacherId;

    public Student() {
    }

    public Student(Integer id) {
        this.id = id;
    }

    public Student(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class_ getClassId() {
        return classId;
    }

    public void setClassId(Class_ classId) {
        this.classId = classId;
    }

    public Teacher getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Teacher teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.Student[ id=" + id + " ]";
    }

}
