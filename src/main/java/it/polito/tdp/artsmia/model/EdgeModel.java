package it.polito.tdp.artsmia.model;

import java.util.Objects;

public class EdgeModel {

	private ArtObject source;
	private ArtObject target;
	private int peso;
	public ArtObject getSource() {
		return source;
	}
	public void setSource(ArtObject source) {
		this.source = source;
	}
	public ArtObject getTarget() {
		return target;
	}
	public void setTarget(ArtObject target) {
		this.target = target;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	public EdgeModel(ArtObject source, ArtObject target, int peso) {
		this.source = source;
		this.target = target;
		this.peso = peso;
	}
	@Override
	public int hashCode() {
		return Objects.hash(peso, source, target);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EdgeModel other = (EdgeModel) obj;
		return peso == other.peso && Objects.equals(source, other.source) && Objects.equals(target, other.target);
	}
	
	
}