package fr.arpinum.siteTester.domain;

public class Resource {

	public Resource(Site owner, String relativePath) {
		this.owner = owner;
		this.relativePath = relativePath;
	}

	public String fullPath() {
		return owner.getFullPath(this);
	}

	public String relativePath() {
		return relativePath;
	}

	@Override
	public String toString() {
		return fullPath();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + ((relativePath == null) ? 0 : relativePath.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Resource other = (Resource) obj;
		if (owner == null) {
			if (other.owner != null) {
				return false;
			}
		} else if (!owner.equals(other.owner)) {
			return false;
		}
		if (relativePath == null) {
			if (other.relativePath != null) {
				return false;
			}
		} else if (!relativePath.equals(other.relativePath)) {
			return false;
		}
		return true;
	}

	private final String relativePath;
	private final Site owner;
}
