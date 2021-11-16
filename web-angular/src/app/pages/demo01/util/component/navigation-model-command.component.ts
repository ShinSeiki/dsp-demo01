import { Component, OnInit } from '@angular/core';
import { InavEntityCmd } from 'src/app/core/comp/base-model-component.model';
import { BaseModel } from 'src/app/core/model/base-model.model';

@Component({
  selector: 'app-navigation-model-command',
  templateUrl: './navigation-model-command.component.html',
  styleUrls: ['./navigation-model-command.component.scss']
})
export class NavigationModelCommandComponent<T extends BaseModel<T, ID>, ID> implements OnInit {

  navCommand: InavEntityCmd<T, ID>;

  constructor() { }

  ngOnInit(): void {
  }

}
