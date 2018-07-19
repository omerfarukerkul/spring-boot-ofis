/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.database.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author OmerFaruk
 */
@Entity
@Table(name = "teacher", catalog = "springdatabase", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id_"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Teacher.findAll", query = "SELECT t FROM Teacher t")
    , @NamedQuery(name = "Teacher.findById", query = "SELECT t FROM Teacher t WHERE t.id = ?1")
    , @NamedQuery(name = "Teacher.findByName", query = "SELECT t FROM Teacher t WHERE t.name = ?1")
    , @NamedQuery(name = "Teacher.findByBranch", query = "SELECT t FROM Teacher t WHERE t.branch = ?1")})
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
    @Basic(optional = false)
    @Column(name = "branch_", nullable = false, length = 100)
    private String branch;
    @JoinColumn(name = "class_id_", referencedColumnName = "id_", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Class_ class_;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher", fetch = FetchType.LAZY)
    private List<Student> studentList;

    public Teacher() {
    }

    public Teacher(Integer id) {
        this.id = id;
    }

    public Teacher(Integer id, String name, String branch) {
        this.id = id;
        this.name = name;
        this.branch = branch;
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

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Class_ getClass_() {
        return class_;
    }

    public void setClass_(Class_ class_) {
        this.class_ = class_;
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
