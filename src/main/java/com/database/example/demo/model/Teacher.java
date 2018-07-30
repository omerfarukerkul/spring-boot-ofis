/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.database.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

/**
 * @author OmerFaruk
 */
@Entity
@Table(name = "teacher", catalog = "", schema = "", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id_"})})
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Teacher.findAll", query = "SELECT t FROM Teacher t")
        , @NamedQuery(name = "Teacher.findById", query = "SELECT t FROM Teacher t WHERE t.id = ?1")
        , @NamedQuery(name = "Teacher.findByName", query = "SELECT t FROM Teacher t WHERE t.name = ?1")})
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name_", nullable = false, length = 50)
    private String name;
    @JoinColumn(name = "branch_id_", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Branch branchId;
    @JoinColumn(name = "class_id_", referencedColumnName = "id_", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Class_ classId;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacherId", fetch = FetchType.LAZY)
    private List<Student> studentList;

    public Teacher() {
    }

    public Teacher(Integer id) {
        this.id = id;
    }

    public Teacher(Integer id, String name) {
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

    public Branch getBranchId() {
        return branchId;
    }

    public void setBranchId(Branch branchId) {
        this.branchId = branchId;
    }

    public Class_ getClassId() {
        return classId;
    }

    public void setClassId(Class_ classId) {
        this.classId = classId;
    }

    @XmlTransient
    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
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
        if (!(object instanceof Teacher)) {
            return false;
        }
        Teacher other = (Teacher) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.Teacher[ id=" + id + " ]";
    }

}
