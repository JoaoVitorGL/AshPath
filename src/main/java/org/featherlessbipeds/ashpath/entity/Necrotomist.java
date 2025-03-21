package org.featherlessbipeds.ashpath.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.featherlessbipeds.ashpath.validator.Specialization;


@Entity
@Table(name = "necrotomist")
@NamedQueries({
    @NamedQuery(
            name = "Necrotomist.findByName",
            query = "SELECT n FROM Necrotomist n WHERE n.name LIKE :name"
    )
})
// The guy who performs autopsies and stuff
public class Necrotomist
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "necrotomist_id")
    private Long id;
    
    @NotEmpty
    @Pattern(regexp = "^[\\p{L}\\s']{2,40}$", message = "{org.featherlessbipeds.ashpath.entity.Necrotomist.name}")
    @Column(name = "necrotomist_name")
    private String name;
    
    @Specialization
    @Column(name = "necrotomist_specialization", nullable = false)
    private String specialization; // ex: "Autopsy technician"

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "necrotomist_cremation_queue",
            joinColumns = @JoinColumn(name = "necrotomist_id"),
            inverseJoinColumns = @JoinColumn(name = "cremation_queue_id")
    )
    private Set<CremationQueue> cremationQueueSet = new HashSet<>();
    
    @Size(max = 4, message = "{org.featherlessbipeds.ashpath.entity.Necrotomist.deceasedSet}")
    @OneToMany(mappedBy = "necrotomist")
    private Set<Deceased> deceasedSet = new HashSet<>();

    public void addCremationQueue(CremationQueue queue)
    {
        if (this.cremationQueueSet == null)
        {
            this.cremationQueueSet = new HashSet<>();
        }
        this.cremationQueueSet.add(queue);
        queue.getNecrotomistSet().add(this);
    }

    public void addDeceased(Deceased deceased)
    {
        if (this.deceasedSet == null)
        {
            this.deceasedSet = new HashSet<>();
        }
        this.deceasedSet.add(deceased);
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Necrotomist other = (Necrotomist) obj;
        return Objects.equals(this.id, other.id);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
    
    

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public Set<CremationQueue> getCremationQueueSet() {
        return cremationQueueSet;
    }

    public Set<Deceased> getDeceasedSet() {
        return deceasedSet;
    }
    
    
}
