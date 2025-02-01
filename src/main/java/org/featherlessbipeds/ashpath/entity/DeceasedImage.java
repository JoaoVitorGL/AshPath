package org.featherlessbipeds.ashpath.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Arrays;

@Entity
@Table(name = "deceased_img")
public class DeceasedImage {
    // NOTE: The id must NOT be autogenerated. It doesn't make sense.
    @Id
    @Column(name = "deceased_id")
    private Long id;

    @Lob 
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "img", nullable = true) // An array IS an object, so it CAN be null.
    private byte[] img;

    @MapsId // Ensures the ID is shared with the Deceased entity
    @OneToOne
    @JoinColumn(name = "deceased_id")
    private Deceased deceased;

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 97 * hash + Arrays.hashCode(this.img);
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
        final DeceasedImage other = (DeceasedImage) obj;
        return Arrays.equals(this.img, other.img);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public void setDeceased(Deceased deceased) {
        this.deceased = deceased;
    }
    
    

    public Long getId() {
        return id;
    }

    public byte[] getImg() {
        return img;
    }

    public Deceased getDeceased() {
        return deceased;
    }
    
    
}
