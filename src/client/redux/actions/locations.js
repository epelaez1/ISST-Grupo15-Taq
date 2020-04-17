export function setLocation(location) {
	return { type: 'SET_LOCATION', payload: { location } };
}
export function setLocations(locations) {
	return { type: 'SET_LOCATIONS', payload: { locations } };
}

export function removeLocations(locations) {
	return { type: 'REMOVE_LOCATIONS', payload: { locations } };
}

export function removeLocation(location) {
	return { type: 'REMOVE_LOCATION', payload: { location } };
}

export function updateLocations(locations) {
	return { type: 'UPDATE_LOCATIONS', payload: { locations } };
}

export function updateLocation(location) {
	return { type: 'UPDATE_LOCATION', payload: { location } };
}
