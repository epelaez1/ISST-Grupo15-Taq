import React, { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import { scaffolds, getRentalsScaffold } from '../utils/tableScaffolds';
import EditableTable from '../components/EditableTable';
import { RentalStates } from '../../server/constants';
import { getAllRentals, renewRental } from '../utils/api/rentals';
import { getAllLockers } from '../utils/api/lockers';
import { getAllUsers } from '../utils/api/users';

const MyLockers = () => {
	const [rentalsScaffold, setRentalsScaffold] = useState(scaffolds.rentals);
	const users = useSelector((state) => state.users);
	const loggedUser = useSelector((state) => state.loggedUser);
	const lockers = useSelector((state) => state.lockers);
	const requests = useSelector((state) => state.rentals.filter(
		(rental) => rental.userId === loggedUser.id && rental.rentalStateId !== RentalStates.RENTED && rental.rentalStateId !== RentalStates.RETURNED && rental.rentalStateId !== RentalStates.CLAIMED,
	));

	const claimed = useSelector((state) => state.rentals.filter(
		(rental) => rental.userId === loggedUser.id && rental.rentalStateId === RentalStates.CLAIMED,
	));

	const activeRentals = useSelector((state) => state.rentals.filter(
		(rental) => rental.rentalStateId === RentalStates.RENTED && rental.userId === loggedUser.id,
	));

	const oldRentals = useSelector((state) => state.rentals.filter(
		(rental) => rental.rentalStateId === RentalStates.RETURNED && rental.userId === loggedUser.id,
	));

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
	console.log(rentalsScaffold.columns);
	return (
		<div>
			<EditableTable
				data={requests}
				title="Solicitudes"
				columns={rentalsScaffold.columns}
				actions={[]}
			/>
			<br />
			<EditableTable
				data={activeRentals}
				title="Alquileres activos"
				columns={rentalsScaffold.columns}
				detailPanel={[]}
				actions={[]}
			/>
			<br />
			<EditableTable
				data={claimed}
				title="Alquileres caaducados"
				columns={rentalsScaffold.columns}
				actions={[{
					icon: 'autorenew',
					tooltip: 'Solicitar renovación',
					onClick: (event, rental) => renewRental(rental),
					position: 'row',
				}]}
			/>
			<br />
			<EditableTable
				data={oldRentals}
				title="Alquileres pasados"
				columns={rentalsScaffold.columns}
				actions={[]}
			/>
		</div>
	);
};

export default MyLockers;
