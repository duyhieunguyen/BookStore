package pq.jdev.b001.bookstore.users.model;

import java.sql.Date;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstname;
    private String lastname;
    private String phone;
    private String Address;
    private String email;
    private int sex;
    private String username;
    private String password;
    private Date birthday;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "role_person",
            joinColumns = @JoinColumn(
                    name = "PERSONID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "ROLEID", referencedColumnName = "id"))
    private Collection<Role> roles;

    public Person() {
    }

	public Person(Long id, String firstname, String lastname, String phone, String address, String email,
			String username, String password, int sex, Date birthday, Collection<Role> roles) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
		this.Address = address;
		this.email = email;
		this.sex = sex;
		this.birthday = birthday;
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}
	
	@Override
	public String toString() {
		return "Person{id=" + id + 
				", firstname=" + firstname + 
				", lastname=" + lastname + 
				", phone=" + phone + 
				", Address=" + Address + 
				", email=" + email + 
				", sex=" + sex +
				", birthday=" + birthday +
				", username=" + username + 
				", password=" + password + 
				", roles=" + roles + "}";
	}	
}