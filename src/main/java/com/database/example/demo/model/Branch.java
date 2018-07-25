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
@Table(name = "branch", catalog = "springdatabase", schema = "", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"branch_name"})
        , @UniqueConstraint(columnNames = {"id"})})
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Branch.findAll", query = "SELECT b FROM Branch b")
        , @NamedQuery(name = "Branch.findById", query = "SELECT b FROM Branch b WHERE b.id = ?1")
        , @NamedQuery(name = "Branch.findByBranchName", query = "SELECT b FROM Branch b WHERE b.branchName = ?1")})
public class Branch implements Serializable, Comparable<Branch> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "branch_name", nullable = false, length = 255)
    private String branchName;
    @JsonIgnore
    @XmlTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "branchId", fetch = FetchType.LAZY)
    private List<Teacher> teacherList;

    public Branch() {
    }

    public Branch(Integer id) {
        this.id = id;
    }

    public Branch(Integer id, String branchName) {
        this.id = id;
        this.branchName = branchName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    @XmlTransient
    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
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
        if (!(object instanceof Branch)) {
            return false;
        }
        Branch other = (Branch) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.Branch[ id=" + id + " ]";
    }

    @Override
    public int compareTo(Branch o) {
        return id.compareTo(o.id);
    }
}
