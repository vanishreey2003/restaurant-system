import React, {useEffect, useState} from 'react';
import axios from 'axios';

function App() {
  const [menu, setMenu] = useState([]);
  const [cart, setCart] = useState({});

  useEffect(() => {
    axios.get('/api/menu').then(res => setMenu(res.data)).catch(err => console.error(err));
  }, []);

  const add = (id) => setCart(c => ({...c, [id]: (c[id] || 0) + 1}));
  const remove = (id) => setCart(c => {
    const nc = {...c}; if(nc[id]) nc[id]--; if(nc[id] <= 0) delete nc[id]; return nc;
  });

  const placeOrder = () => {
    const items = Object.entries(cart).map(([id, qty]) => ({menuItemId: parseInt(id), quantity: qty}));
    if(items.length === 0) { alert('Cart is empty'); return; }
    axios.post('/api/orders', { customerName: 'Guest', items })
      .then(() => { alert('Order placed'); setCart({}); })
      .catch(e => { console.error(e); alert('Failed to place order'); });
  };

  return (
    <div style={{padding:20, fontFamily:'Arial'}}>
      <h1>Restaurant Menu</h1>
      {menu.length === 0 ? <p>Loading menu...</p> :
        menu.map(m => (
          <div key={m.id} style={{border:'1px solid #ddd', padding:10, marginBottom:8}}>
            <strong>{m.name}</strong> — ₹{m.price}<br/>
            <small>{m.description}</small><br/>
            <button onClick={() => add(m.id)}>Add</button>
            <button onClick={() => remove(m.id)} style={{marginLeft:8}}>Remove</button>
            <div>In cart: {cart[m.id] || 0}</div>
          </div>
        ))
      }
      <div style={{marginTop:20}}>
        <button onClick={placeOrder}>Place Order</button>
      </div>
    </div>
  );
}

export default App;
