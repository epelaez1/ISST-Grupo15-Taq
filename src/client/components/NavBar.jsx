import React, { useEffect } from 'react';
import AppBar from '@material-ui/core/AppBar';
import IconButton from '@material-ui/core/IconButton';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import MenuIcon from '@material-ui/icons/Menu';
import FormGroup from '@material-ui/core/FormGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Switch from '@material-ui/core/Switch';
import { useSelector, useDispatch } from 'react-redux';
import { makeAdmin } from '../redux/actions/session';
import getSession from '../utils/api/session';

function NavBar(props) {
	const {
		handleDrawerToggle, classes,
	} = props;
	const loggedUser = useSelector((state) => state.loggedUser);
	const auth = loggedUser.isAdmin;
	const dispatch = useDispatch();
	useEffect(() => {
		getSession();
	}, []);
	const handleChange = () => {
		dispatch(makeAdmin());
	};
	return (
		<AppBar position="fixed" className={classes.appBar}>
			<Toolbar>
				<IconButton
					color="inherit"
					aria-label="open drawer"
					edge="start"
					onClick={handleDrawerToggle}
					className={classes.menuButton}
				>
					<MenuIcon />
				</IconButton>
				<Typography variant="h6" noWrap className={classes.title}>
					DAT - Taquillas
				</Typography>
				<Typography variant="h6" style={{ color: 'inherit', textDecoration: 'inherit', paddingRight: '20px' }}>
					{loggedUser.name}
				</Typography>
				<FormGroup className={classes.appBar}>
					<FormControlLabel
						control={<Switch checked={auth} onChange={handleChange} aria-label="login switch" />}
						label={auth ? 'Admin' : 'Admin'}
					/>
				</FormGroup>
			</Toolbar>
		</AppBar>
	);
}

export default NavBar;
