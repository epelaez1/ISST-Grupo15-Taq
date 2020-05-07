import React, { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { scaffolds } from '../utils/tableScaffolds';
import {
	getAllLocations, createLocation,
	updateLocation, removeLocation,
} from '../utils/api/locations';
import EditableTable from '../components/EditableTable';

const Locations = () => {
	const locationsScaffold = scaffolds.locations;
	const data = useSelector((state) => state.locations);
	useEffect(() => {
		getAllLocations();
	}, []);

	return (
		<EditableTable
			data={data}
			title={locationsScaffold.title}
			columns={locationsScaffold.columns}
			create={createLocation}
			update={updateLocation}
			remove={removeLocation}
			actions={[]}
			editable
			showEmpty
		/>

	);
};

export default Locations;
