import React, { useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { createMuiTheme, ThemeProvider } from '@material-ui/core/styles';
import {
	fetchGet, fetchPost,
	fetchDelete, fetchPut,
} from '../util';
import {
	setLocation, setLocations,
	updateLocation, removeLocation,
} from '../redux/actions/locations';
import EditableTable from '../components/EditableTable';
import Layout from '../components/Layout';

const Locations = () => {
	const dispatch = useDispatch();

	const create = (newLocation) => fetchPost('/api/v1/admin/location', newLocation)
		.then((r) => r.json())
		.then((location) => dispatch(setLocation(location)));

	const update = (newLocation, oldLocation) => {
		const url = `/api/v1/admin/location/${oldLocation.id}`;
		return fetchPut(url, newLocation)
			.then((r) => r.json())
			.then((location) => {
				console.log("'Pasa'");
				return location;
			})
			.then((location) => dispatch(updateLocation(location)));
	};

	const remove = (location) => {
		const url = `/api/v1/admin/location/${location.id}`;
		return fetchDelete(url, {})
			.then((r) => r.json())
			.then(() => dispatch(removeLocation(location)));
	};

	useEffect(() => {
		fetchGet('/api/v1/admin/locations')
			.then((r) => r.json())
			.then((locations) => dispatch(setLocations(locations)));
	});

	return (
		<EditableTable
			model="locations"
			create={create}
			update={update}
			remove={remove}
		/>

	);
};

export default Locations;
