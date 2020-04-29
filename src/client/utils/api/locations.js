import {
	fetchGet, fetchPost,
	fetchDelete, fetchPut,
} from '../asyncRequests';

import {
	setLocation, setLocations,
	updateLocation as updateLocationAction,
	removeLocation as removeLocationAction,
} from '../../redux/actions/locations';

import store from '../../redux/store';

const { dispatch } = store;

export const getAllLocations = () => fetchGet('/api/v1/admin/locations')
	.then((r) => r.json())
	.then((locations) => dispatch(setLocations(locations)));

export const createLocation = (newLocation) => fetchPost('/api/v1/admin/location', newLocation)
	.then((r) => r.json())
	.then((location) => dispatch(setLocation(location)));

export const updateLocation = (newLocation, oldLocation) => {
	const url = `/api/v1/admin/location/${oldLocation.id}`;
	return fetchPut(url, newLocation)
		.then((r) => r.json())
		.then((location) => {
			console.log("'Pasa'");
			return location;
		})
		.then((location) => dispatch(updateLocationAction(location)));
};

export const removeLocation = (location) => {
	const url = `/api/v1/admin/location/${location.id}`;
	return fetchDelete(url, {})
		.then((r) => r.json())
		.then(() => dispatch(removeLocationAction(location)));
};
