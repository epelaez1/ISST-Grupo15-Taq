import React, { useEffect } from 'react';
import { scaffolds } from '../utils/tableScaffolds';
import {
	getAllLocations, createLocation,
	updateLocation, removeLocation,
} from '../utils/api/locations';
import EditableTable from '../components/EditableTable';

const Locations = () => {
	const locationsScaffold = scaffolds.locations;

	useEffect(() => {
		getAllLocations();
	}, []);

	return (
		<EditableTable
			model="locations"
			title={locationsScaffold.title}
			columns={locationsScaffold.columns}
			create={createLocation}
			update={updateLocation}
			remove={removeLocation}
		/>

	);
};

export default Locations;
