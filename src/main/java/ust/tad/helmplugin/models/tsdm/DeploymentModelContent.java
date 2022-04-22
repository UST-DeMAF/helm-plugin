package ust.tad.helmplugin.models.tsdm;

import java.net.URL;
import java.util.List;
import java.util.Objects;

public class DeploymentModelContent {

    private URL location;    

    private List<Line> lines;

    
    public DeploymentModelContent() {
    }

    public DeploymentModelContent(URL location, List<Line> lines) throws InvalidNumberOfLinesException {        
        if(lines.isEmpty()){
            throw new InvalidNumberOfLinesException("A DeploymentModelContent must have at least one Line");
        } else {
            this.location = location;
            this.lines = lines;
        }
    }

    public URL getLocation() {
        return this.location;
    }

    public void setLocation(URL location) {
        this.location = location;
    }

    public List<Line> getLines() {
        return this.lines;
    }

    public void setLines(List<Line> lines) throws InvalidNumberOfLinesException {
        if(lines.isEmpty()){
            throw new InvalidNumberOfLinesException("A DeploymentModelContent must have at least one Line");
        } else {
            this.lines = lines;
        }
    }

    public DeploymentModelContent location(URL location) {
        setLocation(location);
        return this;
    }

    public DeploymentModelContent lines(List<Line> lines) throws InvalidNumberOfLinesException {
        setLines(lines);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof DeploymentModelContent)) {
            return false;
        }
        DeploymentModelContent deploymentModelContent = (DeploymentModelContent) o;
        return Objects.equals(location, deploymentModelContent.location) && Objects.equals(lines, deploymentModelContent.lines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, lines);
    }

    @Override
    public String toString() {
        return "{" +
            " location='" + getLocation() + "'" +
            ", lines='" + getLines() + "'" +
            "}";
    }

    public void addLine(Line line) {
        this.lines.add(line);
        for(Line lineElement : this.lines) {
            if(lineElement.getNumber() == 0) {
                lines.remove(lineElement);
            }
        }
    }
}
