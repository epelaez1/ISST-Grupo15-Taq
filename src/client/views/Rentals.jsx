import React, { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import { scaffolds, getRentalsScaffold } from '../utils/tableScaffolds';
import EditableTable from '../components/EditableTable';
import { RentalStates } from '../../server/constants';
import {
	getAllRentals, createRental,
	updateRental, removeRental,
	claimRental, endRental,
} from '../utils/api/rentals';

import { getAllLockers } from '../utils/api/lockers';
import { getAllUsers } from '../utils/api/users';

const Rentals = () => {
	const [rentalsScaffold, setRentalsScaffold] = useState(scaffolds.rentals);
	const users = useSelector((state) => state.users);
	const lockers = useSelector((state) => state.lockers);
	const allRentals = useSelector((state) => state.rentals);
	const activeRentals = useSelector((state) => state.rentals.filter(
		(rental) => rental.rentalStateId === RentalStates.RENTED,
	));
	const claimedRentals = useSelector((state) => state.rentals.filter(
		(rental) => rental.rentalStateId === RentalStates.CLAIMED,
	));
	console.log(allRentals);
	useEffect(() => {
		getAllRentals();
	}, []);
	useEffect(() => {
		getAllUsers();
	}, []);

	useEffect(() => {
		getAllLockers();
	}, []);

	useEffect(() => {
		setRentalsScaffold(getRentalsScaffold(lockers, users));
	}, [lockers, users]);

	return (
		<div>
			<EditableTable
				data={activeRentals}
				title="Alquileres activos"
				columns={rentalsScaffold.columns}
				actions={[{
					icon: 'event_bussy',
					tooltip: 'Reclamar fin de alquiler',
					onClick: (event, rental) => claimRental(rental),
					position: 'row',
				}]}
			/>
			<br />
			<EditableTable
				data={claimedRentals}
				title="Pendientes de devolución de llaves"
				columns={rentalsScaffold.columns}
				actions={[{
					icon: 'vpn_key',
					tooltip: 'Finalizar alquiler',
					onClick: (event, rental) => endRental(rental),
					position: 'row',
				}]}
			/>
			<br />
			<EditableTable
				data={allRentals}
				title="Todos los alquileres (Histórico)"
				columns={rentalsScaffold.columns}
				create={createRental}
				update={updateRental}
				remove={removeRental}
				actions={[]}
				editable
				showEmpty
			/>
		</div>
	);
};

export default Rentals;
