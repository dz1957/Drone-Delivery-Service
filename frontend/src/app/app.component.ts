import { ChangeDetectorRef, Component} from '@angular/core';
import { HttpClient, HttpClientModule, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { SharedModule} from "./shared.module";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  standalone: true,
  imports: [SharedModule]
})
export class AppComponent {
  speed: any;
  title(title: any) {
    throw new Error('Method not implemented.');
  }
  currentResponse: string = '';
  history:  { params: string; response: string }[] = [];

  // Wrap history in an Observable
  history$: Observable<{ params: string; response: string }[]> = of([]);

  constructor(private http: HttpClient, private cdr: ChangeDetectorRef) {}

  createStore(storeName: string, initialRevenue: number, x: number, y: number) {
    if (isNaN(initialRevenue) || isNaN(x) || isNaN(y)) {
      console.error('All parameters must be valid numbers');
      this.currentResponse = 'All parameters must be valid numbers';
      return;
    }

    const queryParams = new HttpParams()
      .set('storeName', storeName)
      .set('initialRevenue', initialRevenue.toString())
      .set('x', x.toString())
      .set('y', y.toString());
    const url = 'http://localhost:3001/api/store';

    // Upon success or error, update the history immutably and trigger change detection
    this.http.post(url, null, { responseType: 'text', params: queryParams })
      .subscribe({
        next: (responseData) => {
          this.currentResponse = responseData;
          this.history = [...this.history, {
            params: `storeName=${storeName}, initialRevenue=${initialRevenue}, x=${x}, y=${y}`,
            response: responseData
          }];
          console.log(this.history);
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        },
        error: (error) => {
          this.currentResponse = `Failed to create store: ${error.statusText}`;
          this.history = [...this.history, { 
            params: `storeName=${storeName}, initialRevenue=${initialRevenue}, x=${x}, y=${y}`, 
            response: `Error: ${error.statusText}` 
          }];
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        }
      });
  }

  trackByFn(index: any, item: any) {
    return index; // or item.id
  }
  

  displayStores() {
    const url = 'http://localhost:3001/api/stores'; // Endpoint as given in the curl command

    this.http.get(url, { responseType: 'text' })
      .subscribe({
        next: (responseData) => {
          // Process and display the list of stores
          this.currentResponse = responseData;
          // Add this to the history
          this.history.push({ params: 'Display all stores', response: responseData });
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        },
        error: (error) => {
          // Error handling
          console.error('Failed to display stores:', error);
          this.currentResponse = `Failed to display stores: ${error.statusText}`;
          this.history.push({ params: 'Display all stores', response: `Error: ${error.statusText}` });
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        }
      });
  }

  sellItem(storeName: string, itemName: string, weight: string) {
    const queryParams = new HttpParams()
      .set('storeName', storeName)
      .set('itemName', itemName)
      .set('weight', weight);
    const url = 'http://localhost:3001/api/sellItem';

    this.http.post(url, null, { responseType: 'text', params: queryParams })
      .subscribe({
        next: (responseData) => {
          this.currentResponse = responseData;
          this.history = [...this.history, { 
            params: `Sell Item: storeName=${storeName}, itemName=${itemName}, weight=${weight}`, 
            response: responseData 
          }];
          console.log(this.history);
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        },
        error: (error) => {
          this.currentResponse = `Failed to sell item: ${error.statusText}`;
          this.history = [...this.history, { 
            params: `Sell Item: storeName=${storeName}, itemName=${itemName}, weight=${weight}`, 
            response: `Error: ${error.statusText}` 
          }];
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        }
      });
  }

  displayItems(storeName: string) {
    const queryParams = new HttpParams().set('storeName', storeName);
    const url = 'http://localhost:3001/api/displayItems';

    this.http.get(url, { responseType: 'text', params: queryParams })
      .subscribe({
        next: (responseData) => {
          this.currentResponse = responseData;
          this.history = [...this.history, { 
            params: `Display Items: storeName=${storeName}`, 
            response: responseData 
          }];
          console.log(this.history);
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        },
        error: (error) => {
          this.currentResponse = `Failed to display items: ${error.statusText}`;
          this.history = [...this.history, { 
            params: `Display Items: storeName=${storeName}`, 
            response: `Error: ${error.statusText}` 
          }];
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        }
      });
  }

  makePilot(
    pilotAccount: string,
    firstName: string,
    lastName: string,
    phoneNumber: string,
    taxId: string,
    licenseId: string,
    expLevel: number
  ) {
    const queryParams = new HttpParams()
      .set('pilotAccount', pilotAccount)
      .set('firstName', firstName)
      .set('lastName', lastName)
      .set('phoneNumber', phoneNumber)
      .set('taxId', taxId)
      .set('licenseId', licenseId)
      .set('expLevel', expLevel.toString());
    const url = 'http://localhost:3001/api/makePilot';

    this.http.post(url, null, { responseType: 'text', params: queryParams })
      .subscribe({
        next: (responseData) => {
          this.currentResponse = responseData;
          this.history = [...this.history, {
            params: `Pilot Account=${pilotAccount}, First Name=${firstName}, Last Name=${lastName}, Phone Number=${phoneNumber}, Tax ID=${taxId}, License ID=${licenseId}, Experience Level=${expLevel}`,
            response: responseData
          }];
          console.log(this.history);
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        },
        error: (error) => {
          this.currentResponse = `Failed to make pilot: ${error.statusText}`;
          this.history = [...this.history, {
            params: `Pilot Account=${pilotAccount}, First Name=${firstName}, Last Name=${lastName}, Phone Number=${phoneNumber}, Tax ID=${taxId}, License ID=${licenseId}, Experience Level=${expLevel}`,
            response: `Error: ${error.statusText}`
          }];
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        }
      });
  }

  displayPilots() {
    const url = 'http://localhost:3001/api/displayPilots';

    this.http.get(url, { responseType: 'text' })
      .subscribe({
        next: (responseData) => {
          this.currentResponse = responseData;
          this.history.push({ params: 'Display all pilots', response: responseData });
          console.log(this.history);
          this.history$ = of(this.history);
        },
        error: (error) => {
          console.error('Failed to display pilots:', error);
          this.currentResponse = `Failed to display pilots: ${error.statusText}`;
          this.history.push({ params: 'Display all pilots', response: `Error: ${error.statusText}` });
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        }
      });
  }
 
  makeDrone(
    storeName: string,
    droneId: string,
    liftingCapacity: number,
    fuel: number,
    fuelCapacity: number,
    fuelRate: number,
    refuelingRate: number,
    speed: number
  ) {
    const queryParams = new HttpParams()
      .set('storeName', storeName)
      .set('droneId', droneId)
      .set('liftingCapacity', liftingCapacity.toString())
      .set('fuel', fuel.toString())
      .set('fuelCapacity', fuelCapacity.toString())
      .set('fuelRate', fuelRate.toString())
      .set('refuelingRate', refuelingRate.toString())
      .set('speed', speed.toString());
    const url = 'http://localhost:3001/api/makeDrone';
  
    this.http.post(url, null, { responseType: 'text', params: queryParams })
      .subscribe({
        next: (responseData) => {
          this.currentResponse = responseData;
          this.history = [...this.history, {
            params: `Make Drone: Store Name=${storeName}, Drone ID=${droneId}, Lifting Capacity=${liftingCapacity}, Fuel =${fuel}, Fuel Capacity=${fuelCapacity}, Fuel Rate=${fuelRate}, Refueling Rate=${refuelingRate}, Speed=${speed}`,
            response: responseData
          }];
          console.log(this.history);
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        },
        error: (error) => {
          this.currentResponse = `Failed to make drone: ${error.statusText}`;
          this.history = [...this.history, {
            params: `Make Drone: Store Name=${storeName}, Drone ID=${droneId}, Lifting Capacity=${liftingCapacity}, Fuel =${fuel}, Fuel Capacity=${fuelCapacity}, Fuel Rate=${fuelRate}, Refueling Rate=${refuelingRate}, Speed=${speed}`,
            response: `Error: ${error.statusText}`
          }];
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        }
      });
    //this.displayDrones(storeName);
  }
  
  flyDrone(storeName: string, droneId: string, pilotId: string) {
    const queryParams = new HttpParams()
      .set('storeName', storeName)
      .set('droneId', droneId)
      .set('pilotId', pilotId);
    const url = 'http://localhost:3001/api/flyDrone';
  
    this.http.put(url, null, { responseType: 'text', params: queryParams })
      .subscribe({
        next: (responseData) => {
          this.currentResponse = responseData;
          this.history = [...this.history, {
            params: `Fly Drone: Store Name=${storeName}, Drone ID=${droneId}, Pilot ID=${pilotId}`,
            response: responseData
          }];
          console.log(this.history);
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        },
        error: (error) => {
          this.currentResponse = `Failed to fly drone: ${error.statusText}`;
          this.history = [...this.history, {
            params: `Fly Drone: Store Name=${storeName}, Drone ID=${droneId}, Pilot ID=${pilotId}`,
            response: `Error: ${error.statusText}`
          }];
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        }
      });
    //this.displayDrones(storeName);
  }
  
  displayDrones(storeName: string) {
    const queryParams = new HttpParams().set('storeName', storeName);
    const url = 'http://localhost:3001/api/displayDrones';
  
    this.http.get(url, { responseType: 'text', params: queryParams })
      .subscribe({
        next: (responseData) => {
          this.currentResponse = responseData;
          this.history.push({ params: `Display Drones: Store Name=${storeName}`, response: responseData });
          console.log(this.history);
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        },
        error: (error) => {
          console.error('Failed to display drones:', error);
          this.currentResponse = `Failed to display drones: ${error.statusText}`;
          this.history.push({ params: `Display Drones: Store Name=${storeName}`, response: `Error: ${error.statusText}` });
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        }
      });
  }

  createCustomer(account: string, firstName: string, lastName: string, phoneNumber: string, rating: number, credit: number, x: number, y: number) {
    if (isNaN(rating) || isNaN(credit) || isNaN(x) || isNaN(y)) {
      console.error('Rating, credit, x, and y must be valid numbers');
      this.currentResponse = 'Rating, credit, x, and y must be valid numbers';
      return;
    }

    const queryParams = new HttpParams()
      .set('account', account)
      .set('firstName', firstName)
      .set('lastName', lastName)
      .set('phoneNumber', phoneNumber)
      .set('rating', rating.toString())
      .set('credit', credit.toString())
      .set('x', x.toString())
      .set('y', y.toString());
    const url = 'http://localhost:3001/api/makeCustomer';

    this.http.post(url, null, { responseType: 'text', params: queryParams })
      .subscribe({
        next: (responseData) => {
          this.currentResponse = responseData;
          this.history = [...this.history, {
            params: `account=${account}, firstName=${firstName}, lastName=${lastName}, phoneNumber=${phoneNumber}, rating=${rating}, credit=${credit}, x=${x}, y=${y}`,
            response: responseData
          }];
          console.log(this.history);
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        },
        error: (error) => {
          this.currentResponse = `Failed to create customer: ${error.statusText}`;
          this.history = [...this.history, { 
            params: `account=${account}, firstName=${firstName}, lastName=${lastName}, phoneNumber=${phoneNumber}, rating=${rating}, credit=${credit}, x=${x}, y=${y}`, 
            response: `Error: ${error.statusText}` 
          }];
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        }
      });
  }

  displayCustomers() {
    const url = 'http://localhost:3001/api/displayCustomers';

    this.http.get(url, { responseType: 'text' })
      .subscribe({
        next: (responseData) => {
          this.currentResponse = responseData;
          this.history.push({ params: 'Display all customers', response: responseData });
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        },
        error: (error) => {
          this.currentResponse = `Failed to display customers: ${error.statusText}`;
          this.history.push({ params: 'Display all customers', response: `Error: ${error.statusText}` });
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        }
      });
  }

  startOrder(storeName: string, orderId: string, droneId: string, customerAccount: string) {
    const queryParams = new HttpParams()
      .set('storeName', storeName)
      .set('orderId', orderId)
      .set('droneId', droneId)
      .set('customerAccount', customerAccount);
    const url = 'http://localhost:3001/api/startOrder';

    this.http.post(url, null, { responseType: 'text', params: queryParams })
      .subscribe({
        next: (responseData) => {
          this.currentResponse = responseData;
          this.history = [...this.history, {
            params: `storeName=${storeName}, orderId=${orderId}, droneId=${droneId}, customerAccount=${customerAccount}`,
            response: responseData
          }];
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        },
        error: (error) => {
          this.currentResponse = `Failed to start order: ${error.statusText}`;
          this.history = [...this.history, { 
            params: `storeName=${storeName}, orderId=${orderId}, droneId=${droneId}, customerAccount=${customerAccount}`, 
            response: `Error: ${error.statusText}` 
          }];
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        }
      });
  }

  displayOrders(storeName: string) {
    const queryParams = new HttpParams().set('storeName', storeName);
    const url = 'http://localhost:3001/api/displayOrders';

    this.http.get(url, { responseType: 'text', params: queryParams })
      .subscribe({
        next: (responseData) => {
          this.currentResponse = responseData;
          this.history.push({ params: `Display orders for storeName=${storeName}`, response: responseData });
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        },
        error: (error) => {
          this.currentResponse = `Failed to display orders: ${error.statusText}`;
          this.history.push({ params: `Display orders for storeName=${storeName}`, response: `Error: ${error.statusText}` });
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        }
      });
  }

  requestItem(storeName: string, orderId: string, itemId: string, quantity: number, unitPrice: number) {
    if (isNaN(quantity) || isNaN(unitPrice)) {
      console.error('Quantity and unit price must be valid numbers');
      this.currentResponse = 'Quantity and unit price must be valid numbers';
      return;
    }

    const queryParams = new HttpParams()
      .set('storeName', storeName)
      .set('orderID', orderId)
      .set('itemId', itemId)
      .set('quantity', quantity.toString())
      .set('unitPrice', unitPrice.toString());
    const url = 'http://localhost:3001/api/requestItem';

    this.http.put(url, null, { responseType: 'text', params: queryParams })
      .subscribe({
        next: (responseData) => {
          this.currentResponse = responseData;
          this.history = [...this.history, {
            params: `storeName=${storeName}, orderID=${orderId}, itemId=${itemId}, quantity=${quantity}, unitPrice=${unitPrice}`,
            response: responseData
          }];
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        },
        error: (error) => {
          this.currentResponse = `Failed to request item: ${error.statusText}`;
          this.history = [...this.history, { 
            params: `storeName=${storeName}, orderID=${orderId}, itemId=${itemId}, quantity=${quantity}, unitPrice=${unitPrice}`, 
            response: `Error: ${error.statusText}` 
          }];
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        }
      });
  }

  purchaseOrder(storeName: string, orderId: string) {
    const queryParams = new HttpParams()
      .set('storeName', storeName)
      .set('orderId', orderId);
    const url = 'http://localhost:3001/api/purchaseOrder';

    this.http.put(url, null, { responseType: 'text', params: queryParams })
      .subscribe({
        next: (responseData) => {
          this.currentResponse = responseData;
          this.history = [...this.history, {
            params: `storeName=${storeName}, orderId=${orderId}`,
            response: responseData
          }];
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        },
        error: (error) => {
          this.currentResponse = `Failed to purchase order: ${error.statusText}`;
          this.history = [...this.history, {
            params: `storeName=${storeName}, orderId=${orderId}`,
            response: `Error: ${error.statusText}`
          }];
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        }
      });
  }

  cancelOrder(storeName: string, orderId: string) {
    const queryParams = new HttpParams()
      .set('storeName', storeName)
      .set('orderId', orderId);
    const url = 'http://localhost:3001/api/cancelOrder';

    this.http.delete(url, { responseType: 'text', params: queryParams })
      .subscribe({
        next: (responseData) => {
          this.currentResponse = responseData;
          this.history = [...this.history, {
            params: `storeName=${storeName}, orderId=${orderId}`,
            response: responseData
          }];
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        },
        error: (error) => {
          this.currentResponse = `Failed to cancel order: ${error.statusText}`;
          this.history = [...this.history, {
            params: `storeName=${storeName}, orderId=${orderId}`,
            response: `Error: ${error.statusText}`
          }];
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        }
      });
  }

  transferOrder(storeName: string, orderId: string, newDroneId: number) {
    if (isNaN(newDroneId)) {
      console.error('New Drone ID must be a valid number');
      this.currentResponse = 'New Drone ID must be a valid number';
      return;
    }

    const queryParams = new HttpParams()
      .set('storeName', storeName)
      .set('orderId', orderId)
      .set('newDroneId', newDroneId.toString());
    const url = 'http://localhost:3001/api/transferOrder';

    this.http.put(url, null, { responseType: 'text', params: queryParams })
      .subscribe({
        next: (responseData) => {
          this.currentResponse = responseData;
          this.history = [...this.history, {
            params: `storeName=${storeName}, orderId=${orderId}, newDroneId=${newDroneId}`,
            response: responseData
          }];
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        },
        error: (error) => {
          this.currentResponse = `Failed to transfer order: ${error.statusText}`;
          this.history = [...this.history, {
            params: `storeName=${storeName}, orderId=${orderId}, newDroneId=${newDroneId}`,
            response: `Error: ${error.statusText}`
          }];
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        }
      });
  }

  displayEfficiency() {
    const url = 'http://localhost:3001/api/displayEfficiency';

    this.http.get(url, { responseType: 'text' })
      .subscribe({
        next: (responseData) => {
          this.currentResponse = responseData;
          this.history = [...this.history, {
            params: 'Display efficiency',
            response: responseData
          }];
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        },
        error: (error) => {
          this.currentResponse = `Failed to display efficiency: ${error.statusText}`;
          this.history = [...this.history, {
            params: 'Display efficiency',
            response: `Error: ${error.statusText}`
          }];
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        }
      });
  }

  displayTime() {
    const url = 'http://localhost:3001/api/displayTime';
  
    this.http.get(url, { responseType: 'text' })
      .subscribe({
        next: (responseData) => {
          this.currentResponse = responseData;
          this.history = [...this.history, {
            params: 'Display time',
            response: responseData
          }];
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        },
        error: (error) => {
          this.currentResponse = `Failed to display time: ${error.statusText}`;
          this.history = [...this.history, {
            params: 'Display time',
            response: `Error: ${error.statusText}`
          }];
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        }
      });
  }
 
  setRefuelingRate(storeName: string, droneId: string, refuelingRate: number) {
    const url = `http://localhost:3001/api/setRefuelingRate`;
    const params = new HttpParams()
      .set('storeName', storeName)
      .set('droneId', droneId)
      .set('refuelingRate', refuelingRate.toString());
  
    this.http.put(url, null, { responseType: 'text', params })
      .subscribe({
        next: (responseData) => {
          this.currentResponse = responseData;
          this.history = [...this.history, {
            params: `Set refueling rate for store ${storeName}, drone ${droneId}, rate ${refuelingRate}`,
            response: responseData
          }];
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        },
        error: (error) => {
          this.currentResponse = `Failed to set refueling rate: ${error.statusText}`;
          this.history = [...this.history, {
            params: `Set refueling rate for store ${storeName}, drone ${droneId}, rate ${refuelingRate}`,
            response: `Error: ${error.statusText}`
          }];
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        }
      });
  }
  
  setFuelRate(storeName: string, droneId: string, fuelRate: number) {
    const url = `http://localhost:3001/api/setFuelRate`;
    const params = new HttpParams()
      .set('storeName', storeName)
      .set('droneId', droneId)
      .set('fuelRate', fuelRate.toString());
  
    this.http.put(url, null, { responseType: 'text', params })
      .subscribe({
        next: (responseData) => {
          this.currentResponse = responseData;
          this.history = [...this.history, {
            params: `Set fuel rate for store ${storeName}, drone ${droneId}, rate ${fuelRate}`,
            response: responseData
          }];
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        },
        error: (error) => {
          this.currentResponse = `Failed to set fuel rate: ${error.statusText}`;
          this.history = [...this.history, {
            params: `Set fuel rate for store ${storeName}, drone ${droneId}, rate ${fuelRate}`,
            response: `Error: ${error.statusText}`
          }];
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        }
      });
  }

  
  requestReturn(
    storeName: string,
    orderId: string,
    customerAccount: string,
    droneId: string,
    returnWindow: number, 
    maxReturnableOrders: number,
    itemAndQtyInput: string
  ) {
    const url = `http://localhost:3001/api/return`;
  
    // Constructing HttpParams in the same way as in setFuelRate function
    let params = new HttpParams()
      .set('storeName', storeName)
      .set('orderId', orderId)
      .set('customerAccount', customerAccount)
      .set('droneId', droneId)
      .set('returnWindow', returnWindow.toString())
      .set('maxReturnableOrders', maxReturnableOrders.toString())
      .set('itemAndQty', itemAndQtyInput);  // Ensure this is the format the server expects
  
    // Making the POST request with params
    this.http.post(url, null, { responseType: 'text', params })
      .subscribe({
        next: (responseData) => {
          this.currentResponse = responseData;
          this.history = [...this.history, {
            params: `Request return for store ${storeName}, order ${orderId}, account ${customerAccount}, drone ${droneId}, items ${itemAndQtyInput}, returnWindow ${returnWindow}, maxReturnableOrders ${maxReturnableOrders}`,
            response: responseData
          }];
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        },
        error: (error) => {
          this.currentResponse = `Failed to request return: ${error.statusText}`;
          this.history = [...this.history, {
            params: `Request return for store ${storeName}, order ${orderId}, account ${customerAccount}, drone ${droneId}, items ${itemAndQtyInput}, returnWindow ${returnWindow}, maxReturnableOrders ${maxReturnableOrders}`,
            response: `Error: ${error.statusText}`
          }];
          this.history$ = of(this.history);
          this.cdr.detectChanges();
        }
      });
  }
  
  
  refreshHistory() {
    this.cdr.detectChanges();
  }

  resetHistory() {
    this.history = [];
    this.cdr.detectChanges(); // Trigger change detection
  }
}


