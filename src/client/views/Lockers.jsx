import React, { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import { getLockersScaffold, scaffolds } from '../utils/tableScaffolds';
import {
	getAllLockers, createLocker,
	updateLocker, removeLocker,
} from '../utils/api/lockers';
import { getAllLocations } from '../utils/api/locations';
import EditableTable from '../components/EditableTable';

const Lockers = () => {
	const [lockersScaffold, setLockersScaffold] = useState(scaffolds.lockers);
	const locations = useSelector((state) => state.locations);

	useEffect(() => {
		getAllLockers();
	}, []);

	useEffect(() => {
		getAllLocations();
	}, []);

	useEffect(() => {
		const lockerScafold = getLockersScaffold(locations);
		setLockersScaffold(lockerScafold);
	}, [locations]);

	return (
		<EditableTable
			model="lockers"
			columns={lockersScaffold.columns}
			title={lockersScaffold.title}
			create={createLocker}
			update={updateLocker}
			remove={removeLocker}
		/>

	);
};

export default Lockers;
