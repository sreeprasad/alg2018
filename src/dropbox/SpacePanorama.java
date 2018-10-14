package dropbox;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * NASA selects Dropbox as its official partner, and we’re tasked with managing
 * a panorama for the universe. The Hubble telescope (or some other voyager we
 * have out there) will occasionally snap a photo of a sector of the universe,
 * and transmit it to us. You are to help write a data structure to manage this.
 * For the purpose of this problem, assume that the observable universe has been
 * divided into 2D sectors. Sectors are indexed by x- and y-coordinates.
 */
class File {
    public File(String path) {}
    public Boolean exists() { return true;}
    public byte[] read() { return new byte[1];}
    public void write(byte[] bytes) {}
}

class Image {
    public Image(byte[] bytes) {}
    byte[] getBytes() { return new byte[1]; } // no more than 1MB in size
}

class Sector {
    public Sector(int x, int y) {}
    int getX() { return 0; }
    int getY() { return 0; }
}

/**
 * row-major indexing to be consistent.
 */
public class SpacePanorama {

    private Set<Sector> set;
    /**
     * initializes the data structure. rows x cols is the sector layout.
     * width, height can be as large as 1K each.
     */
    public SpacePanorama(int rows, int cols) {
        set = new LinkedHashSet<>();
    }

    /**
     * The Hubble will occasionally call this (via some radio wave communication)
     * to report new imagery for the sector at (y, x)
     * Images can be up to 1MB in size.
     */
    public void update(int y, int x, Image image) {
        File file = new File("./" + x + "-" + y);
        if (file.exists()) {
            // file.clear();
            set.remove("x" + "-" + "y");
        }
        byte[] bytes = null;
        while ((bytes = image.getBytes()) != null) {
            file.write(bytes);
        }
        set.add(new Sector(x, y));
    }

    /**
     * NASA will occasionally call this to check the view of a particular sector.
     */
    public Image fetch(int y, int x) {
        File file = new File("./" + x + "-" + y);
        if (file.exists()) {
            return new Image(file.read());
        }
        return null;
    }
    /**
     * return the 2D index of the sector that has the stalest data.
     */
    public Sector getStalestSector() {
        Iterator<Sector> i = set.iterator();
        if (i.hasNext()) {
            return i.next();
        }
        return null;
    }
}
