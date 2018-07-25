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


@Entity
@Table(name = "class", catalog = "springdatabase", schema = "", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id_"})})
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Class_.findAll", query = "SELECT c FROM Class_ c")
        , @NamedQuery(name = "Class_.findById", query = "SELECT c FROM Class_ c WHERE c.id = ?1")
        , @NamedQuery(name = "Class_.findByClassName", query = "SELECT c FROM Class_ c WHERE c.className = ?1")
        , @NamedQuery(name = "Class_.findByClassBranch", query = "SELECT c FROM Class_ c WHERE c.classBranch = ?1")})
public class Class_ implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_", nullable = false)
    private Integer id;
    @Column(name = "class_name_", length = 50)
    private String className;
    @Column(name = "class_branch_", length = 50)
    private String classBranch;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classId", fetch = FetchType.LAZY)
    private List<Teacher> teacherList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classId", fetch = FetchType.LAZY)
    private List<Student> studentList;

    public Class_() {
    }

    public Class_(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassBranch() {
        return classBranch;
    }

    public void setClassBranch(String classBranch) {
        this.classBranch = classBranch;
    }

    @XmlTransient
    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
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
        if (!(object instanceof Class_)) {
            return false;
        }
        Class_ other = (Class_) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.Class_[ id=" + id + " ]";
    }

}
