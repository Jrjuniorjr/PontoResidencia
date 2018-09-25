import { Component, OnInit, Input } from '@angular/core';
import { AbstractControl } from '@angular/forms';

type TInputType = 'text' | 'number' | 'password'

@Component({
  selector: 'app-form-control',
  templateUrl: './form-control.component.html',
  styleUrls: ['./form-control.component.css']
})
export class FormControlComponent implements OnInit {

  @Input() control: AbstractControl 
  @Input() label: string;
  @Input() type:string
  @Input() options:string[]


  private selectedOption:string = '0'
  private _hasFocus:boolean = false
  
  constructor() { 
      
  }

  ngOnInit() {
    if(this.type === 'select')
      this.control.setValue(this.selectedOption)
  }

  @Input()
  set hasFocus(value: string) {
    this._hasFocus = value != "false"
  }

}
