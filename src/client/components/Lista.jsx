import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import InputLabel from '@material-ui/core/InputLabel';
import MenuItem from '@material-ui/core/MenuItem';
import FormHelperText from '@material-ui/core/FormHelperText';
import FormControl from '@material-ui/core/FormControl';
import Select from '@material-ui/core/Select';
import Button from '@material-ui/core/Button';
import CheckIcon from '@material-ui/icons/Check';
import CloseIcon from '@material-ui/icons/Close';
import { green, red } from '@material-ui/core/colors';





const useStyles = makeStyles((theme) => ({
  formControl: {
    margin: theme.spacing(2),
    minWidth: 120,
    width: '40ch'
  },
  selectEmpty: {
    marginTop: theme.spacing(2),
  },
  button: {
    margin: theme.spacing(1),
    
  },
}));
const ITEM_HEIGHT = 48;
const ITEM_PADDING_TOP = 8;
const MenuProps = {
  PaperProps: {
    style: {
      maxHeight: ITEM_HEIGHT * 4.5 + ITEM_PADDING_TOP,
      width: 250,
    },
  },
};

export default function SimpleSelect() {

  const classes = useStyles();
  const [taquilla, setTaquilla] = React.useState('');
  const [ready, setReady] = React.useState(false);
  const [selected, setSelected] = React.useState(false);

  const handleChange = (event) => {
    setTaquilla(event.target.value);
  };

  const handleReady = (event) => {
    setReady(true);
  };
  const handleSelect = (event) => {
    setSelected(true);
  };

  return (
    <div>
        <div>
      <FormControl className={classes.formControl}>
        <InputLabel id="demo-simple-select-label">Selecciona una taquilla de la lista</InputLabel>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          value={taquilla}
          onChange={handleChange}
          MenuProps={MenuProps}
          onClick={handleReady}
        >
          <MenuItem value={"Taquilla 68"}>Taquilla 68</MenuItem>
          <MenuItem value={"Taquilla 69"}>Taquilla 69</MenuItem>
          <MenuItem value={"Taquilla 70"}>Taquilla 70</MenuItem>
          <MenuItem value={"Taquilla 71"}>Taquilla 71</MenuItem>
          <MenuItem value={"Taquilla 72"}>Taquilla 72</MenuItem>
          <MenuItem value={"Taquilla 73"}>Taquilla 73</MenuItem>
          <MenuItem value={"Taquilla 74"}>Taquilla 74</MenuItem>
          <MenuItem value={"Taquilla 75"}>Taquilla 75</MenuItem>
          <MenuItem value={"Taquilla 76"}>Taquilla 76</MenuItem>
        </Select>      
           
      </FormControl>
     {ready
     ? [<Button
     variant="contained"
     color="primary"
     
     className={classes.button}
     endIcon={<CheckIcon />}
   >
     Confirmar
   </Button>,
<Button
variant="contained"
color="secondary"
className={classes.button}
endIcon={<CloseIcon />}
>
Cancelar
</Button>]
     :null
   }
      
      
      </div>
<div>
Has seleccionado la taquillas {taquilla} del edificio A. Por favor revise que la taquilla seleccionada sea la que desea.

</div>
</div>
  );
}
/*{this.state.ready == true ? 
    (<div>  
        <Button
      variant="contained"
      color="green"
      className={classes.button}
      endIcon={<CheckIcon />}
    >
      Confirmar
    </Button>
    <Button
      variant="contained"
      color="red"
      className={classes.button}
      endIcon={<CloseIcon />}
    >
      Cancelar
    </Button>

      </div>):(<div>  
                            
          
                 </div> )
        };*/